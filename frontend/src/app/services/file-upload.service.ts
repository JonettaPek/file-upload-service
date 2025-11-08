import { Injectable } from '@angular/core';
import { FileStatistics } from '../models/file-statistics.model';
import { ApiResponse } from '../models/api-response.model';

@Injectable({
  providedIn: 'root',
})
export class FileUploadService {
  private apiUrl = 'http://localhost:8080/api/files';

  constructor() {}

  async uploadAndProcessFile(file: File): Promise<ApiResponse<FileStatistics>> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    const response = await fetch(`${this.apiUrl}/upload`, {
      method: 'POST',
      body: formData,
    });

    return await response.json();
  }

  async getFileStatisticsByFileId(
    fileId: string
  ): Promise<ApiResponse<FileStatistics>> {
    const response = await fetch(`${this.apiUrl}/upload/${fileId}`);

    return await response.json();
  }
}
